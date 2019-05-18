import React from 'react';
import { Col, Icon, Row, Typography } from 'antd';

import styles from './Header.module.css'
import Avatar from "./avatar/Avatar"

export default () => (
    <header className={styles.header}>

        <Row gutter={16} type="flex" className={styles.nav}>
            <Col span={8}>
                <Typography.Title level={3} className={styles.title}><a href="/">Now!</a></Typography.Title>
            </Col>
            <Col span={14}>
            </Col>
            <Col span={2}>
                <Icon type="more" />
            </Col>
        </Row>

        <Row gutter={16} type="flex" justify="space-around" align="middle" className={styles.user}>
            <Col span={8}>
                <Avatar />
            </Col>
            <Col span={12}>Лента</Col>
            <Col span={4} style={{textAlign: 'right'}}>
                <Icon type="search" />
            </Col>
        </Row>

    </header>
)
