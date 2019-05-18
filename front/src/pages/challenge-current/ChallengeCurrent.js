import React from "react"
import { Affix, Button, Typography } from "antd"

import style from './ChallengeCurrent.module.css'
import Challenge from "./Challenge"

export default () => (
    <React.Fragment>
        <div className={style.content}>
            <Typography.Title level={4}>Ваше текущее задание</Typography.Title>

            <Challenge />
        </div>
        <Affix offsetBottom={32} className={style["action-bar"]}>
            <Button shape="round" size="large" >Не смог :(</Button>
            <div className={style.separator}/>
            <Button type="primary" shape="round" size="large">Готово!</Button>
        </Affix>
    </React.Fragment>
)
