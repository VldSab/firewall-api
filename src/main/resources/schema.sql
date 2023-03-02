CREATE TABLE IF NOT EXISTS public.groups (
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(128),
    comment VARCHAR(512),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.ip_container (
    id BIGINT AUTO_INCREMENT,
    group_id BIGINT NOT NULL,
    ip VARCHAR(128) NOT NULL,
    CONSTRAINT fk_i_groups
        FOREIGN KEY (group_id) REFERENCES public.groups (id),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.port_container (
    id BIGINT AUTO_INCREMENT,
    group_id BIGINT NOT NULL,
    port INT NOT NULL,
    CONSTRAINT fk_p_groups
        FOREIGN KEY (group_id) REFERENCES public.groups (id),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.firewall_rules (
    id BIGINT AUTO_INCREMENT,
    action VARCHAR(16) NOT NULL,
    protocol VARCHAR(8) NOT NULL,
    src_ip VARCHAR(128),
    src_port INT,
    dst_ip VARCHAR(128),
    dst_port INT,
    src_group_id BIGINT,
    dst_group_id BIGINT,
    additional_parameters VARCHAR,
    comment VARCHAR(512),
    CONSTRAINT fk_fr_src_groups
        FOREIGN KEY (src_group_id) REFERENCES public.groups (id),
    CONSTRAINT fk_fr_dst_groups
        FOREIGN KEY (dst_group_id) REFERENCES public.groups (id),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.dlp_rules (
    id BIGINT AUTO_INCREMENT,
    action VARCHAR(16) NOT NULL,
    protocol VARCHAR(8) NOT NULL,
    src_group_id BIGINT,
    dst_group_id BIGINT,
    additional_parameters VARCHAR,
    comment VARCHAR(512),
    sensitive_category VARCHAR(128),
    CONSTRAINT fk_dlp_src_groups
        FOREIGN KEY (src_group_id) REFERENCES public.groups (id),
    CONSTRAINT fk_dlp_dst_groups
        FOREIGN KEY (dst_group_id) REFERENCES public.groups (id),
    PRIMARY KEY (id)
);