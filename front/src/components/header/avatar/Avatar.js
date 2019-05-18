import { Avatar, Col, Row, Typography } from "antd"
import avatar from "./avatar.png"
import styles from "../Header.module.css"
import React from "react"

export default () => (
    <Col gutter={16} type="flex" direction="" justify="space-around" align="middle">
        <Row span={6}>
            <Avatar shape="square" src={avatar} className={styles.avatar}/>
            <Typography.Text>
                80 lvl
            </Typography.Text>
        </Row>
        <Row span={18}>
            Star star
        </Row>

    </Col>
)
