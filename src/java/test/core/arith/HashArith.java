package test.core.arith;

/**
 * Created by woshizbh on 2016/12/21.
 */
public class HashArith {
    public static void main(String[] args) {
        String a="hash";
        System.out.println(a.hashCode());
        a="hash";
        System.out.println(a.hashCode());
        System.out.println(new HashArith().BKDRHash(a));
    }

    //BKDRHash无论是在实际效果还是编码实现中，效果都是最突出的。APHash也是较为优秀的算法。
    // DJBHash,JSHash,RSHash与SDBMHash各有千秋。PJWHash与ELFHash效果最差，但得分相似，其算法本质是相似的。
    public long RSHash(String str)
    {
        int b = 378551;
        int a = 63689;
        long hash = 0;
        for(int i = 0; i < str.length(); i++)
        {
            hash = hash * a + str.charAt(i);
            a = a * b;
        }
        return hash;
    }
    /*JSHash*/
    public long JSHash(String str)
    {
        long hash = 1315423911;
        for(int i = 0; i < str.length(); i++)
            hash ^= ((hash << 5) + str.charAt(i) + (hash >> 2));
        return hash;
    }
    /*PJWHash*/
    public long PJWHash(String str)
    {
        long BitsInUnsignedInt = (long)(4 * 8);
        long ThreeQuarters = (long)((BitsInUnsignedInt * 3) / 4);
        long OneEighth = (long)(BitsInUnsignedInt / 8);
        long HighBits = (long)(0xFFFFFFFF)<<(BitsInUnsignedInt-OneEighth);
        long hash = 0;
        long test = 0;
        for(int i = 0; i < str.length(); i++)
        {
            hash = (hash << OneEighth) + str.charAt(i);
            if((test = hash & HighBits) != 0)
                hash = ((hash ^ (test >> ThreeQuarters)) & (~HighBits));
        }
        return hash;
    }
    /*ELFHash*/
    public long ELFHash(String str)
    {
        long hash = 0;
        long x = 0;
        for(int i = 0; i < str.length(); i++)
        {
            hash = (hash << 4) + str.charAt(i);
            if(( x = hash & 0xF0000000L) != 0)
                hash ^= ( x >> 24);
            hash &= ~x;
        }
        return hash;
    }
    /*BKDRHash*/
    public long BKDRHash(String str)
    {
        long seed = 131;//31131131313131131313etc..
        long hash = 0;
        for(int i = 0; i < str.length(); i++)
            hash = (hash * seed) + str.charAt(i);
        return hash;
    }
    /*SDBMHash*/
    public long SDBMHash(String str)
    {
        long hash = 0;
        for(int i = 0; i < str.length(); i++)
            hash = str.charAt(i) + (hash << 6) + (hash << 16) - hash;
        return hash;
    }
    /*DJBHash*/
    public long DJBHash(String str)
    {
        long hash = 5381;
        for(int i = 0; i < str.length(); i++)
            hash = ((hash << 5) + hash) + str.charAt(i);
        return hash;
    }
    /*DEKHash*/
    public long DEKHash(String str)
    {
        long hash = str.length();
        for(int i = 0; i < str.length(); i++)
            hash = ((hash << 5) ^ (hash >> 27)) ^ str.charAt(i);
        return hash;
    }
    /*BPHash*/
    public long BPHash(String str)
    {
        long hash=0;
        for(int i = 0;i < str.length(); i++)
            hash = hash << 7 ^ str.charAt(i);
        return hash;
    }
    /*FNVHash*/
    public long FNVHash(String str)
    {
        long fnv_prime = 0x811C9DC5;
        long hash = 0;
        for(int i = 0; i < str.length(); i++)
        {
            hash *= fnv_prime;
            hash ^= str.charAt(i);
        }
        return hash;
    }
    /*APHash*/
    public long APHash(String str)
    {
        long hash = 0xAAAAAAAA;
        for(int i = 0; i < str.length(); i++)
        {
            if((i & 1) == 0)
                hash ^=((hash << 7) ^ str.charAt(i) ^ (hash >> 3));
            else
                hash ^= (~((hash << 11) ^ str.charAt(i) ^ (hash >> 5)));
        }
        return hash;
    }
}
