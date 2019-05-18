import React from "react"
import { Affix, Button, Col, Row, Typography } from "antd"

import style from './ChallengeCurrent.module.css'
import Challenge from "./Challenge"

export default () => (
    <React.Fragment>
        <div className={style.content}>
            <Typography.Title level={4}>Ваше текущее задание</Typography.Title>
            <Challenge/>
        </div>
        <Affix offsetBottom={32} className={style["action-bar"]}>
            <Row >
                <Col span={10}>
                    <Button style={{width: '100%'}} size="large">Отказаться</Button>
                </Col>
                <Col span={4}>
                </Col>
                <Col span={10}>
                    <Button type="primary" shape="round" size="large">Готово!</Button>
                </Col>
            </Row>
        </Affix>
    </React.Fragment>
)
