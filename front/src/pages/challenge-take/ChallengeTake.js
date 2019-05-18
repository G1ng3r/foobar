import { withRouter } from "react-router"

// TODO: Отправка на бэк и получение id
export default withRouter(({ history }) => {
    history.push('/challenge/current')
    return null
})
