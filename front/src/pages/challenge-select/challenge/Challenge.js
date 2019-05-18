import { Avatar, Button, Card, Typography } from "antd"
import React from "react"
import style from './Challenge.module.css'
import { withRouter } from "react-router"
import cn from 'classnames'

const handleDoItClick = (history) => () => history.push('/challenge/take')

const Header = ({ type, avatar, name }) => (
    <div>
        <Avatar size="small" icon="user"/>
        <Typography.Text className={style.text}>{name}</Typography.Text>
    </div>
)

export default withRouter(({ challenge, type, history }) => (
    <Card title={<Header type={type} {...challenge}/>} bordered={true}
          className={cn(style.card, {
              [style.daily]: type === 'daily'
          })}>
        <div style={{ display: 'flex' }}>
            <Typography.Text style={{marginRight: 8}}>{challenge.date}</Typography.Text>
            <Typography.Text>{challenge.description}</Typography.Text>
        </div>
        <Button className={style.button} type="primary" shape="round" size={'large'} icon="exclamation"
                onClick={handleDoItClick(history)}>
            Взять
        </Button>
    </Card>
))
