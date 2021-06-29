package run.micromall.micromall.service.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * FilenameUtils
 *
 * @author Administrator
 * @since 2021/1/28
 */
public class FilenameUtils {
    /**
     * 获取文件的名称
     * <p>
     * 例子：\\upload\\2021\\1\\9959fa38-304f-4079-8ef4-e9c0a12f74b4.jpg<br>
     * 结果：9959fa38-304f-4079-8ef4-e9c0a12f74b4
     *
     * @param filename
     * @return
     */
    public static String getBasename(@NonNull String filename) {
        Assert.hasText(filename, "Filename must not be blank");

        int separatorLastIndex = filename.lastIndexOf(File.separator);

        if (separatorLastIndex == filename.length() - 1) {
            return StringUtils.EMPTY;
        }

        if (separatorLastIndex >= 0 && separatorLastIndex < filename.length() - 1) {
            filename = filename.substring(separatorLastIndex + 1);
        }

        // Find last dot
        int dotLastIndex = filename.lastIndexOf(".");

        String[] split = filename.split("\\.");

        List<String> extList = Arrays.asList("gz", "bz2");

        if (extList.contains(split[split.length - 1]) && split.length >= 3) {
            return filename.substring(0, filename.substring(0, dotLastIndex).lastIndexOf('.'));
        }

        if (dotLastIndex < 0) {
            return filename;
        }

        return filename.substring(0, dotLastIndex);
    }

    /**
     * 获取文件的扩展名<br>
     * <p>
     * 例子：\\upload\\2021\\1\\9959fa38-304f-4079-8ef4-e9c0a12f74b4.jpg<br>
     * 结果：jpg
     *
     * @param filename 路径
     * @return
     */
    @NonNull
    public static String getExtension(@NonNull String filename) {
        Assert.hasText(filename, "Filename must not be blank");

        // Find the last slash
        int separatorLastIndex = filename.lastIndexOf(File.separator);

        if (separatorLastIndex == filename.length() - 1) {
            return StringUtils.EMPTY;
        }

        if (separatorLastIndex >= 0 && separatorLastIndex < filename.length() - 1) {
            filename = filename.substring(separatorLastIndex + 1);
        }

        // Find last dot
        int dotLastIndex = filename.lastIndexOf(".");

        if (dotLastIndex < 0) {
            return StringUtils.EMPTY;
        }

        String[] split = filename.split("\\.");

        List<String> extList = Arrays.asList("gz", "bz2");

        if (extList.contains(split[split.length - 1]) && split.length >= 3) {
            return filename.substring(filename.substring(0, dotLastIndex).lastIndexOf('.') + 1);
        }

        return filename.substring(dotLastIndex + 1);
    }
}
