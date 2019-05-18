import React from "react"
import { Tabs } from "antd"
import Friend from "./Friend"

//FIXME: С бэка
const friends = [
    {
        name: 'Олег Дегтев',
        avatar: ''
    },
    {
        name: 'Костя Лобов',
        avatar: ''
    },
    {
        name: 'Тони Старк',
        avatar: ''
    },
    {
        name: 'Саян Базарсадаев',
        avatar: ''
    },
]

const { TabPane } = Tabs

export default class ChallengeGive extends React.Component {

    state = {
        tab: "1"
    }

    changeTab = (tab) => {
        this.setState({ tab })
    }

    render() {
        return(
            <React.Fragment>
                <Tabs style={{ textAlign: 'center'}} defaultActiveKey="1" onChange={this.changeTab}>
                    <TabPane tab="Друзья" key="1">
                        {friends.map(friend => (
                            <Friend key={friend.name} friend={friend} />
                        ))}
                    </TabPane>
                    <TabPane tab="Рядом" key="2">
                        {friends.map(friend => (
                            <Friend key={friend.name} friend={friend} />
                        ))}
                    </TabPane>
                    <TabPane tab="Все" key="3">
                        {friends.map(friend => (
                            <Friend key={friend.name} friend={friend} />
                        ))}
                    </TabPane>
                </Tabs>
            </React.Fragment>
        )
    }
}
