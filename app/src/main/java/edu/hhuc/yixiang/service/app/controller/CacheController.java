package edu.hhuc.yixiang.service.app.controller;

import edu.hhuc.yixiang.common.base.BaseResponse;
import edu.hhuc.yixiang.common.dto.AnyInputDTO;
import edu.hhuc.yixiang.common.dto.IdDTO;
import edu.hhuc.yixiang.common.dto.OperationLogDTO;
import edu.hhuc.yixiang.service.core.CacheService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/8/29 10:49:00
 */
@RestController
@RequestMapping("/cache")
public class CacheController {
    @Autowired
    private CacheService cacheService;

    @Autowired
    @Qualifier(value = "caffeineCacheManager")
    private CacheManager caffeineCacheManager;

    @GetMapping("/random")
    public BaseResponse<String> randomCode() {
        return BaseResponse.ofSuccess(cacheService.randomCode());
    }

    @PostMapping("/operation")
    public BaseResponse<OperationLogDTO> operation(@RequestBody @Valid IdDTO idDTO) {
        return BaseResponse.ofSuccess(cacheService.queryOperation(idDTO.getId()));
    }

    @PostMapping("/queryUser")
    public BaseResponse<Collection<String>> queryUser(@RequestBody @Valid AnyInputDTO inputDTO) {
        cacheService.queryUser(inputDTO);
        return BaseResponse.ofSuccess(caffeineCacheManager.getCacheNames());
    }
}
