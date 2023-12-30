package edu.hhuc.yixiang.common.base;

import edu.hhuc.yixiang.common.enums.SortEnum;
import edu.hhuc.yixiang.common.exception.IllegalRequestException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/30 15:02:32
 */
@Data
public class Sorter {
    /**
     * 排序键
     */
    private String sortKey;
    /**
     * 排序方式
     */
    private String sortBy;

    /**
     * true：升序
     * false：降序
     * null：不排序
     */
    private Boolean asc;


    /**
     * 用来处理排序键，比如前端指定的排序字段是updateAt，但是数据库字段是update_at
     * @param sorter
     * @param sorterMapping
     * @return
     */
    public static Sorter defaultSorter(Sorter sorter, Map<String, String> sorterMapping) {
        if (Objects.isNull(sorter) || StringUtils.isAnyBlank(sorter.getSortKey(), sorter.getSortBy())) {
            return defaultSorter();
        }
        if (Objects.isNull(sorterMapping) || sorterMapping.containsKey(sorter.getSortKey())) {
            throw new IllegalRequestException(BaseResultCode.REQUEST_INVALID_ERROR.getResultMessage(), BaseResultCode.REQUEST_INVALID_ERROR.getResultCode());
        }
        sorter.setSortKey(sorterMapping.get(sorter.getSortKey()));
        sorter.setAsc(SortEnum.ASC.getCode().equalsIgnoreCase(sorter.getSortKey()));
        return sorter;
    }

    public static Sorter defaultSorter() {
        Sorter sorter = new Sorter();
        sorter.setSortKey("updated_at");
        sorter.setSortBy(SortEnum.DESC.getCode());
        sorter.setAsc(false);
        return sorter;
    }
}
