package cc.aguesuka.bencode;


import cc.aguesuka.bencode.util.ByteUtil;
import cc.aguesuka.bencode.util.HexUtil;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 数组包装类,不可变对象,不能包装null
 *
 * @author :yangmingyuxing
 * 2019/6/30 16:59
 */
public final class BencodeByteArray implements IBencode, Comparable<BencodeByteArray> {
    final private static BencodeByteArray BENCODE_BYTE_ARRAY = new BencodeByteArray(new byte[0]);
    public static BencodeByteArray empty(){
        return BENCODE_BYTE_ARRAY;
    }
    private byte[] data;

    public BencodeByteArray(byte[] data) {
        this.data = data.clone();
    }

    @SuppressWarnings("WeakerAccess")
    public byte[] getBytes() {
        return data.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BencodeByteArray)) {
            return false;
        }
        BencodeByteArray that = (BencodeByteArray) o;
        return Arrays.equals(getBytes(), that.getBytes());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getBytes());
    }

    @Override
    public String toString() {
        if (ByteUtil.isUtf8(ByteBuffer.wrap(getBytes()))) {
            return new String(getBytes(), StandardCharsets.UTF_8);
        }
        return HexUtil.encode(getBytes());
    }

    @Override
    public int compareTo(BencodeByteArray o) {
        return ByteUtil.compareBytes(this.getBytes(), o.getBytes());
    }
}
