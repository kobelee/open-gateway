CREATE DATABASE `open-gateway-manage`;
use open-gateway-manage;
CREATE TABLE `application` (
    `id` bigint(20) NOT NULL COMMENT 'pk',
    `application_code` varchar(30) DEFAULT NULL COMMENT '应用编码',
    `application_name` varchar(50) DEFAULT NULL COMMENT '应用名',
    `entry_url` varchar(200) DEFAULT NULL COMMENT '入口url,例: http://a.com/service',
    `application_desc` varchar(200) DEFAULT NULL,
    `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `record_version` int(10) DEFAULT NULL,
    `creator` varchar(50) DEFAULT NULL,
    `updater` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `idx_application_code` (`application_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `api_consumer` (
    `id` bigint(20) NOT NULL COMMENT 'pk',
    `consumer_code` varchar(30) DEFAULT NULL COMMENT '接口消费者编码',
    `consumer_name` varchar(30) DEFAULT NULL COMMENT '接口消费者名称',
    `consumer_desc` varchar(255) DEFAULT NULL COMMENT '消费者描述',
    `public_key` varchar(30) DEFAULT NULL COMMENT '公钥',
    `private_key` varchar(30) DEFAULT NULL COMMENT '私钥',
    `secret_id` varchar(50) DEFAULT NULL COMMENT '验签密钥id',
    `secret_key` varchar(50) DEFAULT NULL COMMENT '验签密钥',
    `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `record_version` int(10) DEFAULT NULL,
    `creator` varchar(50) DEFAULT NULL,
    `updater` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `idx_ac_secret_id` (`secret_id`),
    KEY `idx_ac_consumer_code` (`consumer_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `api` (
    `id` bigint(20) NOT NULL COMMENT 'pk',
    `api_code` varchar(30) DEFAULT NULL COMMENT '接口编码',
    `api_name` varchar(30) DEFAULT NULL COMMENT '接口名称',
    `api_desc` varchar(255) DEFAULT NULL,
    `application_id` bigint(20) DEFAULT NULL COMMENT '应用名称',
    `application_code` varchar(50) DEFAULT NULL COMMENT '应用编码',
    `application_name` varchar(100) DEFAULT NULL COMMENT '应用名称',
    `access_way` varchar(20) DEFAULT NULL COMMENT '请求方式，same_registry_center：同一注册中心;http_proxy:http代理',
    `method` varchar(50) DEFAULT NULL COMMENT '方法名（对应前端传入的标识）',
    `route_path` varchar(100) DEFAULT NULL COMMENT '目标路由路径(目标应用的路径)',
    `http_url` varchar(100) DEFAULT NULL COMMENT '目标url（http代理方式时生效）',
    `http_method` varchar(20) DEFAULT NULL COMMENT '访问目标的httpmethod  get post put delete等',
    `api_version` varchar(20) DEFAULT NULL COMMENT '接口版本',
    `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `record_version` int(10) DEFAULT NULL,
    `creator` varchar(50) DEFAULT NULL,
    `updater` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `idx_api_interface_code` (`api_code`),
    KEY `idx_api_application_id` (`application_id`),
    KEY `idx_api_method` (`method`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `api_subscribe` (
    `id` bigint(20) NOT NULL COMMENT 'pk',
    `api_id` bigint(20) DEFAULT NULL COMMENT '接口id,api.id关联',
    `application_id` bigint(20) DEFAULT NULL COMMENT '应用id,application.id关联',
    `api_consumer_id` bigint(20) DEFAULT NULL COMMENT '消费者id, api_consumer.id关联',
    `subscribe_config_id` bigint(20) DEFAULT NULL COMMENT '订阅配置id,subscribe_config.id关联',
    `subscribe_desc` varchar(255) DEFAULT NULL COMMENT '订阅描述',
    `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `record_version` int(10) DEFAULT NULL,
    `creator` varchar(50) DEFAULT NULL,
    `updater` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `idx_as_application_id` (`application_id`),
    KEY `idx_as_api_consumer_id` (`api_consumer_id`),
    KEY `idx_as_subscribe_config_id` (`subscribe_config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `api_subscribe_config` (
    `id` bigint(20) NOT NULL COMMENT 'pk',
    `config_code` varchar(20) DEFAULT NULL COMMENT '配置编码',
    `config_name` varchar(50) DEFAULT NULL COMMENT '配置名',
    `config_desc` varchar(300) DEFAULT NULL COMMENT '配置描述',
    `rate_limit_on` tinyint(1) DEFAULT NULL COMMENT '是否启用限流',
    `rate_limiter_config` json DEFAULT NULL COMMENT '限流配置',
    `black_white_list_on` tinyint(1) DEFAULT NULL COMMENT '是否启用黑白名单',
    `black_white_list` json DEFAULT NULL COMMENT '黑白名单',
    `need_sign_charge` tinyint(1) DEFAULT '0' COMMENT '是否需要验签',
    `in_param_decrypt` tinyint(1) DEFAULT '0' COMMENT '入参是否需要解密',
    `out_param_encrypt` tinyint(1) DEFAULT '0' COMMENT '出参是否需要加密',
    `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `record_version` int(10) DEFAULT NULL,
    `creator` varchar(50) DEFAULT NULL,
    `updater` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `idx_asc_config_code` (`config_code`),
    KEY `idx_asc_config_name` (`config_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




