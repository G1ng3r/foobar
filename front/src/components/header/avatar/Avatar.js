import { Avatar, Col, Icon, Row, Typography } from "antd"
import avatar from "./avatar.png"
import styles from "../Header.module.css"
import React from "react"

export default () => (
    <Col gutter={16} type="flex" direction="" justify="space-around" align="middle">
        <Row span={6}>
            <Typography.Text className={styles.karma}>
                +2 520
            </Typography.Text>
        </Row>
        <Row span={6}>
            <Avatar shape="square" src={avatar} className={styles.avatar}/>
        </Row>
        <Row span={12} className={styles.badges}>
            <Icon type="star" style={{ color: 'gold' }} />
            <Icon type="star" style={{ color: 'gold' }} />
        </Row>

    </Col>
)
