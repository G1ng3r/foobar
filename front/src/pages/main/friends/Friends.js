import React from "react"
import { Typography } from "antd"
import Friend from "./Friend"

//FIXME: С бэка
const friends = [
    {
        name: 'Олег Дегтев',
        avatar: '',
        lastCompletedChallenge: {
            title: 'Спас галактику!',
            date: '25.04.2019'
        }
    },
    {
        name: 'Костя Лобов',
        avatar: '',
        lastCompletedChallenge: {
            title: 'Победил Таноса',
            date: '23.04.2019'
        }
    },
    {
        name: 'Тони Старк',
        avatar: '',
        lastCompletedChallenge: {
            title: 'Щёлкнул пальцем как боженька',
            date: '22.04.2019'
        }
    },
    {
        name: 'Саян Базарсадаев',
        avatar: '',
        lastCompletedChallenge: {
            title: 'Полежал на диване',
            date: '21.04.2019'
        }
    },
]

export default () => (
    <React.Fragment>
        <Typography.Title level={4}>Друзья</Typography.Title>
        {friends.map(friend => (
            <Friend friend={friend} />
        ))}
    </React.Fragment>
)

