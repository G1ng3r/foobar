import React from "react"
import { Button, Typography } from "antd"
import Friend from "./Friend"

import styles from './Friends.module.css'
import { withRouter } from "react-router"

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

const giveChallenge = (history) => () => {
    history.push('/challenge/give')
}

export default withRouter(({ history }) => (
    <React.Fragment>
        <div className={styles.header}>
            <Typography.Title level={4} className={styles.title}>Друзья</Typography.Title>
            <Button type="primary" className={styles.button} onClick={giveChallenge(history)}>
                Дать задание
            </Button>
        </div>
        {friends.map(friend => (
            <Friend key={friend.name} friend={friend} />
        ))}
        <div className={styles.footer}>
        <Button type="primary" className={styles.button} onClick={giveChallenge(history)}>
            Дать задание
        </Button>
        </div>
    </React.Fragment>
))
