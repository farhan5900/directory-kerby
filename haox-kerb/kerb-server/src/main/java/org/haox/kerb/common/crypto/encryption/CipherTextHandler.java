package org.haox.kerb.common.crypto.encryption;

import org.haox.asn1.type.Asn1Type;
import org.haox.kerb.codec.KrbCodec;
import org.haox.kerb.spec.KrbException;
import org.haox.kerb.spec.type.common.EncryptedData;
import org.haox.kerb.spec.type.common.EncryptionKey;
import org.haox.kerb.spec.type.common.EncryptionType;
import org.haox.kerb.spec.type.common.KrbErrorCode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * A Hashed Adapter encapsulating ASN.1 cipher text engines to
 * perform encrypt() and decrypt() operations.
 */
public class CipherTextHandler
{
    /** a map of the default encryption types to the encryption engine class names */
    private static final Map<EncryptionType, Class<? extends EncryptionEngine>> DEFAULT_CIPHERS;

    // Initialize the list of encyption mechanisms
    static {
        Map<EncryptionType, Class<? extends EncryptionEngine>> map = new HashMap<EncryptionType, Class<? extends EncryptionEngine>>();

        map.put(EncryptionType.DES_CBC_MD5, DesCbcMd5Encryption.class);
        map.put(EncryptionType.DES3_CBC_SHA1_KD, Des3CbcSha1KdEncryption.class);
        map.put(EncryptionType.AES128_CTS_HMAC_SHA1_96, Aes128CtsSha1Encryption.class);
        map.put(EncryptionType.AES256_CTS_HMAC_SHA1_96, Aes256CtsSha1Encryption.class);
        map.put(EncryptionType.RC4_HMAC, ArcFourHmacMd5Encryption.class);

        DEFAULT_CIPHERS = Collections.unmodifiableMap(map);
    }


    /**
     * Performs an encode and an encrypt.
     *
     * @param key The key to use for encrypting.
     * @param usage The key usage.
     * @return The Kerberos EncryptedData.
     * @throws org.apache.directory.shared.kerberos.exceptions.KerberosException
     */
    public EncryptedData seal(EncryptionKey key, Asn1Type message, KeyUsage usage) throws KrbException {
        try
        {
            byte[] encoded = KrbCodec.encode(message);
            return encrypt(key, encoded, usage);
        } catch (ClassCastException cce)
        {
            throw new KrbException(KrbErrorCode.KRB_AP_ERR_BAD_INTEGRITY, cce);
        }
    }


    public EncryptedData encrypt(EncryptionKey key, byte[] plainText, KeyUsage usage) throws KrbException {
        EncryptionEngine engine = getEngine(key);

        return engine.getEncryptedData(key, plainText, usage);
    }


    /**
     * Decrypt a block of data.
     *
     * @param key The key used to decrypt the data
     * @param data The data to decrypt
     * @param usage The key usage number
     * @return The decrypted data as a byte[]
     * @throws org.apache.directory.shared.kerberos.exceptions.KerberosException If the decoding failed
     */
    public byte[] decrypt(EncryptionKey key, EncryptedData data, KeyUsage usage) throws KrbException {
        EncryptionEngine engine = getEngine(key);

        return engine.getDecryptedData(key, data, usage);
    }


    private EncryptionEngine getEngine(EncryptionKey key) throws KrbException {
        EncryptionType encryptionType = key.getKeyType();

        Class<?> clazz = DEFAULT_CIPHERS.get(encryptionType);

        if (clazz == null)
        {
            throw new KrbException(KrbErrorCode.KDC_ERR_ETYPE_NOSUPP);
        }

        try
        {
            return (EncryptionEngine) clazz.newInstance();
        }
        catch (IllegalAccessException iae)
        {
            throw new KrbException(KrbErrorCode.KDC_ERR_ETYPE_NOSUPP, iae);
        }
        catch (InstantiationException ie)
        {
            throw new KrbException(KrbErrorCode.KDC_ERR_ETYPE_NOSUPP, ie);
        }
    }
}
