package edu.hhuc.yixiang.repository.dao;

import com.mybatisflex.core.paginate.Page;
import edu.hhuc.yixiang.common.base.Sorter;
import edu.hhuc.yixiang.common.dto.OperationLogDTO;
import edu.hhuc.yixiang.common.entity.OperationLog;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/30 16:02:30
 */
public interface OperationLogDao {
    void saveOrUpdate(OperationLogDTO record);

    Page<OperationLog> page(OperationLogDTO record, Sorter sorter);
}
