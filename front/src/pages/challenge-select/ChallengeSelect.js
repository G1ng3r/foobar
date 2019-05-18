import React from "react"
import { Typography } from "antd"
import Challenge from "./challenge/Challenge"

//FIXME: С бэка
const challenges = {
    daily: [
        {
            name: 'Работа по дому',
            avatar: '',
            exp: '5',
            date: '25.04.2019',
            description: 'Помыть посуду'
        }
    ],
    fromFriends: [
        {
            name: 'Олег темный властелин',
            avatar: '',
            exp: '5',
            date: '25.04.2019',
            description: 'Субботник во дворе'
        },
        {
            name: 'Саша Грей',
            avatar: '',
            exp: '5',
            date: '25.04.2019',
            description: 'Почистить бассейн'
        },
        {
            name: 'Олег темный властелин',
            avatar: '',
            exp: '5',
            date: '25.04.2019',
            description: 'Субботник во дворе'
        }
    ]
}

export default () => (
    <React.Fragment>
        {challenges.daily.map(challenge => (
            <Challenge key={challenge.name} challenge={challenge} type="daily"/>
        ))}
        {challenges.fromFriends.map(challenge => (
            <Challenge key={challenge.name} challenge={challenge}/>
        ))}
    </React.Fragment>
)
