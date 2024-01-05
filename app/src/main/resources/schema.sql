create database starry;

create table `operation_log`
(
    `id`              bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `operator_user`   varchar(50)         not null default '' comment '操作人',
    `content`         varchar(1000)       not null default '' comment '日志内容',
    `business_id`     varchar(100)        not null default '' comment '业务数据id',
    `operator_type`   varchar(50)         not null default '' comment '操作类型',
    `operator_module` varchar(100)        not null default '' comment '操作模块',
    `ip`              varchar(50)         not null default '' comment '操作ip',
    `start_time`      datetime            null comment '操作开始时间',
    `end_time`        datetime            null comment '操作结束时间',
    `duration`        int                 null comment '操作耗时',
    `is_deleted`      tinyint(1)          NOT NULL DEFAULT 0 COMMENT 'is_deleted',
    `created_at`      datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'created_at',
    `created_by`      varchar(45)         NOT NULL DEFAULT 'system' COMMENT 'created_by',
    `updated_at`      datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updated_at',
    `updated_by`      varchar(45)         NOT NULL DEFAULT 'system' COMMENT 'updated_by',
    primary key (`id`) USING BTREE,
    KEY `idx_operator_user` (`operator_user`),
    KEY `idx_business_id` (`business_id`),
    KEY `idx_created_at` (`created_at`),
    KEY `idx_updated_at` (`updated_at`)
);

create table `distributed_sequence`
(
    `id`            bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `business_type` varchar(50)         not null default '' comment 'id的业务分类',
    `max_id`        bigint(20)          not null default 1 comment '当前业务可获取的最大id',
    `step`          int(11)             not null default 1 comment '每次获取的步长',
    `remark`        varchar(100)        not null default '' comment '业务类型描述',
    `is_deleted`    tinyint(1)          NOT NULL DEFAULT 0 COMMENT 'is_deleted',
    `created_at`    datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'created_at',
    `created_by`    varchar(45)         NOT NULL DEFAULT 'system' COMMENT 'created_by',
    `updated_at`    datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updated_at',
    `updated_by`    varchar(45)         NOT NULL DEFAULT 'system' COMMENT 'updated_by',
    primary key (`id`) USING BTREE,
    unique uk_business_type (`business_type`) USING BTREE,
    KEY `idx_created_at` (`created_at`),
    KEY `idx_updated_at` (`updated_at`)
) comment '分布式id生成表';