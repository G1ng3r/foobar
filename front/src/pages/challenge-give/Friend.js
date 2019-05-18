import { Avatar, Card } from "antd"
import style from "./Friend.module.css"
import { Typography } from "antd"
import React from "react"


export default ({ friend }) => (
    <Card bordered={true} bodyStyle={{ display: 'flex' }} className={style.card}>
        <Avatar size="small" icon="user" />
        <Typography.Text>{friend.name}</Typography.Text>
    </Card>
)
