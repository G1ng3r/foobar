import React from "react"
// import { Spin } from "antd"

import style from './ChangeGiveToPerson.module.css'
import { Avatar, Button, Radio } from "antd"


export default class ChallengeGiveToPerson extends React.Component {

    render() {
        return (
            <div className={style.content}>
                <Avatar shape="square" size={64}
                        src={`https://randomuser.me/api/portraits/men/${this.props.match.params.id}.jpg`}/>
                <br/>
                <div>
                    <Radio.Group defaultValue="a" buttonStyle="solid">
                        <Radio.Button value="a">Социальное</Radio.Button>
                        <Radio.Button value="b">По дому</Radio.Button>
                        <Radio.Button value="c">Рядом</Radio.Button>
                    </Radio.Group>
                </div>
                <br/>
                <br/>
                <Button type={"primary"} shape="round" size={'large'} icon="exclamation">Выдать</Button>
            </div>
        )
    }
}
