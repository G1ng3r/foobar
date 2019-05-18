import React from "react"
import { Affix, Button } from "antd"

import style from './Main.module.css'

export default () => (
    <React.Fragment>
        <div className={style.content}>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
            <h1>Main page</h1>
        </div>
        <Affix offsetBottom={32} className={style["action-bar"]}>
            <Button  type="primary" shape="round" size={'large'} onClick={() => { /* TODO */ }} >
                Do it now!
            </Button>
        </Affix>
    </React.Fragment>
)
