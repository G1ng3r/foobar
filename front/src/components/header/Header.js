import React from 'react';
import { Col, Icon, Row, Typography } from 'antd';

import styles from './Header.module.css'
import Avatar from "./avatar/Avatar"
import { withRouter } from "react-router"


const pageTitles = {
    '/main': 'Лента',
    '/login': 'Войти',
    '/challenge/search': 'Поиск',
    '/challenge/select': 'Выбор задания',
    '/challenge/take': 'Назначаем задание',
    '/challenge/current': 'Задание',
    '/challenge/give': 'Дать задание',
    '/settings': 'Настройки'
}


const getTitle = (location) => {
    return pageTitles[location.pathname] || ''
}
export default withRouter(({ location }) => (
    <header className={styles.header}>
        <Row gutter={16} type="flex" justify="space-around" align="middle" className={styles.user}>
            <Col span={6}>
                <Typography.Title level={2} className={styles.title}><a href="/main">Now!</a></Typography.Title>
            </Col>
            <Col span={12} >
                <Typography.Title level={2}>{getTitle(location)}</Typography.Title>
            </Col>
            <Col span={6} style={{ textAlign: 'right' }}>
                <Avatar/>
            </Col>
        </Row>
    </header>
))
