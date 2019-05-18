import React from "react"
import { Affix, Button, Col, Modal, Row, Typography } from "antd"
import cn from 'classnames'

import style from './ChallengeCurrent.module.css'
import Challenge from "./Challenge"
import FileUpload from "./FileUpload"

class ChallengeCurrent extends React.Component {

    state = {
        visible: false
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
        return (
            <React.Fragment>
                <div className={style.content}>
                    <Typography.Title level={4}>Ваше текущее задание</Typography.Title>
                    <Challenge/>
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

export default ChallengeCurrent
