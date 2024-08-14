package edu.hhuc.yixiang.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/8/12 16:37:53
 */
@Getter
public class IdDTO {
    @NotNull(message = "id不能为空")
    private Long id;
}
