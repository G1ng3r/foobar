import React from "react"
import style from "./RequestSearch.module.css"


export default () => (
    <div className={style.content}>
        <p className={style.loader}>
            <div className={style.text}>Ищем задание...</div>
        </p>
    </div>
)
