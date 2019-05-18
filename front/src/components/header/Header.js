import React from 'react';
import { Typography, Row, Col, Avatar } from 'antd';

import styles from './Header.module.css'


export default () => (
    <header className={styles.header}>
        <Row gutter={16} type="flex" justify="space-around" align="middle">


            <Col span={6}>
                <Avatar shape="square" size={64} icon="user"/>
            </Col>
            <Col span={18}>
                <Typography.Text>
                    Саян Базарсадаев
                </Typography.Text>
            </Col>

        </Row>
    </header>
)
