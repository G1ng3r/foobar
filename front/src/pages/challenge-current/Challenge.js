import React from "react"
import { Typography, Card } from "antd"
import style from "../main/Main.module.css"


export default () => (
    <Card bordered={true} className={style.event} bodyStyle={{ display: 'flex' }}>
        <div className={style['event-text']}>
            <Typography.Title level={3} style={{ color: 'white' }}>Субботник во дворе</Typography.Title>
            <p><Typography.Text style={{ color: 'white' }}>- Уберите мусор во дворе</Typography.Text></p>
            <p><Typography.Text style={{ color: 'white' }}>- Подметите тротуар</Typography.Text></p>
            <p><Typography.Text style={{ color: 'white' }}>- Отмените щелчок Таноса</Typography.Text></p>
            <p style={{ marginTop: 64 }}><Typography.Text style={{ color: 'white'}}>Награда: +40 в карму</Typography.Text></p>
        </div>
    </Card>
)
