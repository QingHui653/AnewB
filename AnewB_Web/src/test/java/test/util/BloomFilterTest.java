package test.util;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;

/**
 * @author qinghui
 * @Description: guava 布隆过滤器
 * @date 2019/11/18 16:31
 */
public class BloomFilterTest {

    @Test
    public  void bloomFilter() {
        // 总数量
        int total = 1000000;
        BloomFilter<String> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), total);
        // 初始化 10000 条数据到过滤器中
        for (int i = 0; i < total; i++) {
            bf.put("" + i);
        }
        // 判断值是否存在过滤器中
        int count = 0;
        for (int i = 0; i < total + 10000; i++) {
            if (bf.mightContain("" + i)) {
                count++;
            }
        }
        System.out.println("匹配数量 " + count);
    }
}
