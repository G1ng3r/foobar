import React from 'react';
import { Col, Row, Typography } from 'antd';

import styles from './Header.module.css'
import Avatar from "./avatar/Avatar"

export default () => (
    <header className={styles.header}>

        <Typography.Title level={4}>Now!</Typography.Title>

        <Row gutter={16} type="flex" justify="space-around" align="middle" className={styles.user}>
            <Col span={8}>

                <Avatar />
            </Col>
            <Col span={8}>Лента</Col>
            <Col span={8}>Лупа</Col>
        </Row>

    </header>
)
