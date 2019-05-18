import React from "react"
import { Affix, Button, Col, Row, Typography } from "antd"
import cn from 'classnames'

import style from './ChallengeCurrent.module.css'
import Challenge from "./Challenge"

export default () => (
    <React.Fragment>
        <div className={style.content}>
            <Typography.Title level={4}>Ваше текущее задание</Typography.Title>
            <Challenge/>
        </div>
        <Affix offsetBottom={32} className={style["action-bar"]}>
            <Row gutter={40}>
                <Col span={12}>
                    <Button className={cn(style.button, style.red)} size="large">Отказаться</Button>
                </Col>
                <Col span={12}>
                    <Button className={cn(style.button, style.green)} size="large">Готово!</Button>
                </Col>
            </Row>
        </Affix>
    </React.Fragment>
)
