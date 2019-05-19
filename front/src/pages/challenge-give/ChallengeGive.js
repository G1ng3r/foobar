import React from "react"
import { Spin, Tabs } from "antd"
import Friend from "./Friend"
import axios from "axios"


const { TabPane } = Tabs

export default class ChallengeGive extends React.Component {

    state = {
        tab: "1",
        users: []
    }

    loadUsers = () => {

        //FIXME: С бэка
        axios.get('https://randomuser.me/api/?results=50&nat=CA&gender=male').then((data) => {
            const users = data.data.results.map((user, idx) => ({
                name: `${user.name.first} ${user.name.last}`,
                avatar: user.picture.thumbnail,
                id: idx
            }))

            this.setState({ users })
        })

    }

    componentWillMount() {
        this.loadUsers()
    }

    changeTab = (tab) => {
        this.setState({ tab })
    }

    render() {


        if(this.state.users.length === 0) {
            return <Spin style={{display: 'flex', justifyContent: 'center'}} size="large" />
        }
        return(
            <React.Fragment>
                <Tabs style={{ textAlign: 'center'}} defaultActiveKey="1" onChange={this.changeTab}>
                    <TabPane tab="Друзья" key="1">
                        {this.state.users.slice(0, 16).map(friend => (
                            <Friend key={friend.name} friend={friend} />
                        ))}
                    </TabPane>
                    <TabPane tab="Рядом" key="2">
                        {this.state.users.slice(16, 25).map(friend => (
                            <Friend key={friend.name} friend={friend} />
                        ))}
                    </TabPane>
                    <TabPane tab="Все" key="3">
                        {this.state.users.slice(25).map(friend => (
                            <Friend key={friend.name} friend={friend} />
                        ))}
                    </TabPane>
                </Tabs>
            </React.Fragment>
        )
    }
}
