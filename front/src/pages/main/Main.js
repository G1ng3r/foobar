import React from "react"
import { Affix } from "antd"

import style from './Main.module.css'
import Events from "./events/Events"
import Friends from "./friends/Friends"
import DoItButton from "./DoItButton"

export default () => (
    <React.Fragment>
        <div className={style.content}>
            <Events/>

            <Friends/>


        </div>
        <Affix offsetBottom={32} className={style["action-bar"]}>
            <DoItButton />
        </Affix>
    </React.Fragment>
)
