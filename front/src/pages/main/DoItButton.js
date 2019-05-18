import { Button } from "antd"
import React from "react"

import style from './DoItButton.module.css'

export default () => (
    <Button className={style.button} type="primary" shape="round" size={'large'} icon="exclamation" onClick={() => { /* TODO */
    }}>
        Do it now
    </Button>
)
