import React from "react"
import axios from 'axios'
import { Spin, Typography } from "antd"

import styles from './Login.module.css'
import { withRouter } from "react-router"

class Login extends React.Component {

    componentDidMount() {

        console.log(this.props.history)
        axios.post(`${process.env.REACT_APP_API_URL}user/signin`, {
            login: 'sasha69',
            password: 'pass'
        }).then((data) => {
            localStorage.setItem('token', data.headers['access-token'])
            setTimeout(() => {
                this.props.history.push('/main')
            }, 2000)
        })
    }

    render() {
        return (
            <div className={styles.content}>
                <Spin size="large"/>
                <div>
                    <br/>
                    <Typography.Text>Авторизуемся под демо-пользователем</Typography.Text>
                </div>
            </div>
        )
    }
}

export default withRouter(Login)
