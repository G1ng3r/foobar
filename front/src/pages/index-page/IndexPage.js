import React from "react"
import { Link } from 'react-router-dom';

import style from './IndexPage.module.css'

export default () => (
    <div className={style.content}>

        <div className={style.greyBlock}>
            <div className={style.propmoBlock}>
                <p className={style.introText}>Вас приветствует</p>
                <p className={style.introText}>сервис добрых дел</p>
                <p className={style.introText}><span className={style.logo}>NOW!</span></p>

                <p className={style.showIntro}>Смотреть интро</p>
                <Link className={style.skip} to={'/login'}>пропустить</Link>

            </div>
        </div>



    </div>
)
