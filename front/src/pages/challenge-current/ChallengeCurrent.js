import React from "react"
import { Affix, Button, Col, Modal, Row, Spin, Typography } from "antd"
import cn from 'classnames'
import _ from 'lodash'

import axios from '../../utils/axios'

import style from './ChallengeCurrent.module.css'
import Challenge from "./Challenge"
import FileUpload from "./FileUpload"
import { withRouter } from "react-router"

class ChallengeCurrent extends React.Component {

    state = {
        assignment: null,
        visible: false
    }

    componentDidMount() {
        axios.get('challenge/accepted').then(({ data }) => {
            if(data.length > 0) {
                const assignment = data[0]
                axios.get('challengeTypes?limit=150').then(({ data }) => {
                    const assType = _.find(data, {id: assignment.typeId})
                    if (assType) {
                        this.setState({
                            assignment: assType
                        })
                    }
                })
            } else {
                this.props.history.location.push('/main')
            }
        })
    }

    handleDoneClick = () => {
        this.setState({
            visible: true
        })
    }

    handleCancel = () => {
        this.setState({
            visible: false
        })
    }

    render() {
        const { assignment } = this.state
        if (!assignment) {
            return <Spin />
        }

        return (
            <React.Fragment>
                <div className={style.content}>
                    <Typography.Title level={4}>Ваше текущее задание</Typography.Title>
                    <Challenge assignment={assignment}/>
                </div>
                <Affix offsetBottom={32} className={style["action-bar"]}>
                    <Row gutter={40}>
                        <Col span={12}>
                            <Button href="/main" className={cn(style.button, style.red)}
                                    size="large">Отказаться</Button>
                        </Col>
                        <Col span={12}>
                            <Button className={cn(style.button, style.green)} size="large" onClick={this.handleDoneClick}>Готово!</Button>
                        </Col>
                    </Row>
                </Affix>

                <Modal
                    title="Сфотографируй результат"
                    visible={this.state.visible}
                    onOk={this.handleOk}
                    onCancel={this.handleCancel}
                    cancelText={"Отмена"}
                    okText={"Загрузить"}
                >
                    <FileUpload/>
                </Modal>
            </React.Fragment>
        )
    }
}

export default withRouter(ChallengeCurrent)
