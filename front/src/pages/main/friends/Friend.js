import { Avatar, Card, Typography } from "antd"
import React from "react"
import style from './Friend.module.css'

const Header = ({ avatar, name }) => (
    <div>
        <Avatar size="small" icon="user" />
        <Typography.Text>{name}</Typography.Text>
    </div>
)

export default ({ friend }) => (
    <Card title={<Header {...friend}/>} bordered={true} bodyStyle={{ display: 'flex' }} className={style.card}>
        <Typography.Text>{friend.lastCompletedChallenge.date}</Typography.Text>
        <Typography.Text>{friend.lastCompletedChallenge.title}</Typography.Text>
    </Card>
)
