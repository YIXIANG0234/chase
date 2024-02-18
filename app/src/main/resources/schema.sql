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

create table `user`
(
    `id`                bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id`           char(36)            NOT NULL COMMENT '用户id',
    `nick_name`         varchar(50)         NOT NULL COMMENT '用户名',
    `password`          varchar(100)        NOT NULL COMMENT '加密后的密码',
    `salt`              varchar(100)        NOT NULL COMMENT '加密使用的盐',
    `status`            varchar(50)         NOT NULL DEFAULT 'forbidden' COMMENT '状态，activate：启用，forbidden：禁用',
    `recent_login_time` datetime                     DEFAULT NULL COMMENT '上次登录时间',
    `login_count`       bigint(20)          NOT NULL DEFAULT '0' COMMENT '登陆次数',
    `is_deleted`        tinyint(1)          NOT NULL DEFAULT 0 COMMENT 'is_deleted',
    `created_at`        datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'created_at',
    `created_by`        varchar(45)         NOT NULL DEFAULT 'system' COMMENT 'created_by',
    `updated_at`        datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updated_at',
    `updated_by`        varchar(45)         NOT NULL DEFAULT 'system' COMMENT 'updated_by',
    primary key (`id`) USING BTREE,
    unique uk_user_id (`user_id`),
    unique uk_nick_name(`nick_name`),
    KEY `idx_created_at` (`created_at`),
    KEY `idx_updated_at` (`updated_at`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';

create table `role`
(
    `id`         bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `role_code`  varchar(50)                  DEFAULT NULL COMMENT '角色code',
    `parent_id`  varchar(50)                  DEFAULT NULL COMMENT '父级角色',
    `role_name`  varchar(100)                 DEFAULT NULL COMMENT '角色名称',
    `remark`     varchar(100)                 DEFAULT NULL COMMENT '角色描述',
    `is_deleted` tinyint(1)          NOT NULL DEFAULT 0 COMMENT 'is_deleted',
    `created_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'created_at',
    `created_by` varchar(45)         NOT NULL DEFAULT 'system' COMMENT 'created_by',
    `updated_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updated_at',
    `updated_by` varchar(45)         NOT NULL DEFAULT 'system' COMMENT 'updated_by',
    primary key (`id`) USING BTREE,
    unique uk_role_code (`role_code`),
    KEY `idx_parent_id` (`parent_id`),
    KEY `idx_created_at` (`created_at`),
    KEY `idx_updated_at` (`updated_at`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色表';

create table system_resource
(
    `id`              bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`            varchar(50)         NOT NULL COMMENT '资源名称',
    `type`            int(11)             NOT NULL COMMENT '资源类型',
    `permission_code` varchar(50)         NOT NULL COMMENT '访问所需的权限点',
    `parent_id`       bigint(20)          NOT NULL DEFAULT 0 COMMENT '所属上级菜单id',
    `is_deleted`      tinyint(1)          NOT NULL DEFAULT 0 COMMENT 'is_deleted',
    `created_at`      datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'created_at',
    `created_by`      varchar(45)         NOT NULL DEFAULT 'system' COMMENT 'created_by',
    `updated_at`      datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updated_at',
    `updated_by`      varchar(45)         NOT NULL DEFAULT 'system' COMMENT 'updated_by',
    primary key (`id`) USING BTREE,
    unique uk_permission_code (`permission_code`),
    KEY `idx_parent_id` (`parent_id`),
    KEY `idx_created_at` (`created_at`),
    KEY `idx_updated_at` (`updated_at`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='权限表';

create table `role_permission`
(
    `id`              bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `role_id`         bigint(20)          NOT NULL DEFAULT 0 COMMENT '角色id',
    `permission_code` varchar(50)         NOT NULL COMMENT '角色拥有的权限code',
    `is_deleted`      tinyint(1)          NOT NULL DEFAULT 0 COMMENT 'is_deleted',
    `created_at`      datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'created_at',
    `created_by`      varchar(45)         NOT NULL DEFAULT 'system' COMMENT 'created_by',
    `updated_at`      datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updated_at',
    `updated_by`      varchar(45)         NOT NULL DEFAULT 'system' COMMENT 'updated_by',
    primary key (`id`) USING BTREE,
    KEY `idx_role_id_permission_code` (`role_id`, `permission_code`),
    KEY `idx_created_at` (`created_at`),
    KEY `idx_updated_at` (`updated_at`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色-权限关联表';

create table `user_group`
(
    `id`         bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `group_name` varchar(50)         NOT NULL COMMENT '用户组名称',
    `remark`     varchar(100)        NOT NULL COMMENT '用户组描述',
    `is_deleted` tinyint(1)          NOT NULL DEFAULT 0 COMMENT 'is_deleted',
    `created_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'created_at',
    `created_by` varchar(45)         NOT NULL DEFAULT 'system' COMMENT 'created_by',
    `updated_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updated_at',
    `updated_by` varchar(45)         NOT NULL DEFAULT 'system' COMMENT 'updated_by',
    primary key (`id`) USING BTREE,
    KEY `idx_created_at` (`created_at`),
    KEY `idx_updated_at` (`updated_at`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户组';

create table `group_role`
(
    `id`         bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `role_id`    bigint(20)          NOT NULL DEFAULT 0 COMMENT '角色id',
    `group_id`   bigint(20)          NOT NULL DEFAULT 0 COMMENT '用户组id',
    `is_deleted` tinyint(1)          NOT NULL DEFAULT 0 COMMENT 'is_deleted',
    `created_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'created_at',
    `created_by` varchar(45)         NOT NULL DEFAULT 'system' COMMENT 'created_by',
    `updated_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updated_at',
    `updated_by` varchar(45)         NOT NULL DEFAULT 'system' COMMENT 'updated_by',
    primary key (`id`) USING BTREE,
    KEY `idx_role_id_group_id` (`role_id`, `group_id`),
    KEY `idx_created_at` (`created_at`),
    KEY `idx_updated_at` (`updated_at`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户组-角色关联表';

create table `user_group_relation`
(
    `id`         bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id`    bigint(20)          NOT NULL DEFAULT 0 COMMENT '用户id',
    `group_id`   bigint(20)          NOT NULL DEFAULT 0 COMMENT '用户组id',
    `is_deleted` tinyint(1)          NOT NULL DEFAULT 0 COMMENT 'is_deleted',
    `created_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'created_at',
    `created_by` varchar(45)         NOT NULL DEFAULT 'system' COMMENT 'created_by',
    `updated_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updated_at',
    `updated_by` varchar(45)         NOT NULL DEFAULT 'system' COMMENT 'updated_by',
    primary key (`id`) USING BTREE,
    KEY `idx_user_id_group_id` (`user_id`, `group_id`),
    KEY `idx_created_at` (`created_at`),
    KEY `idx_updated_at` (`updated_at`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户-用户组关联表';