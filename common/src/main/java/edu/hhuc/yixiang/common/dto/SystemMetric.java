package edu.hhuc.yixiang.common.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/8/23 10:39:39
 */
@Data
public class SystemMetric {
    public static final BigDecimal KB = new BigDecimal(1024);
    public static final BigDecimal MB = new BigDecimal(1024 * 1024);
    public static final BigDecimal GB = new BigDecimal(1024 * 1024 * 1024);
    private static final String MEMORY_TEXT = "availableProcessors：【%s】，freeMemory：【%s】，maxMemory：【%s】，totalMemory：【%s】";
    private Integer availableProcessors;
    private Long freeMemory;
    private Long maxMemory;
    private Long totalMemory;

    public String memoryForB() {
        return MEMORY_TEXT.formatted(this.availableProcessors, this.freeMemory + "B", this.maxMemory + "B", this.totalMemory + "B");
    }

    public String memoryForKB() {
        return MEMORY_TEXT.formatted(this.availableProcessors,
                new BigDecimal(this.freeMemory).divide(KB, 2, RoundingMode.HALF_UP) + "KB",
                new BigDecimal(this.maxMemory).divide(KB, 2, RoundingMode.HALF_UP) + "KB",
                new BigDecimal(this.totalMemory).divide(KB, 2, RoundingMode.HALF_UP) + "KB");
    }

    public String memoryForMB() {
        return MEMORY_TEXT.formatted(this.availableProcessors,
                new BigDecimal(this.freeMemory).divide(MB, 2, RoundingMode.HALF_UP) + "MB",
                new BigDecimal(this.maxMemory).divide(MB, 2, RoundingMode.HALF_UP) + "MB",
                new BigDecimal(this.totalMemory).divide(MB, 2, RoundingMode.HALF_UP) + "MB");
    }

    public String memoryForGB() {
        return MEMORY_TEXT.formatted(this.availableProcessors,
                new BigDecimal(this.freeMemory).divide(GB, 2, RoundingMode.HALF_UP) + "GB",
                new BigDecimal(this.maxMemory).divide(GB, 2, RoundingMode.HALF_UP) + "GB",
                new BigDecimal(this.totalMemory).divide(GB, 2, RoundingMode.HALF_UP) + "GB");
    }
}
