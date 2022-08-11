import { Style } from '@material-ui/icons';
import { Fragment } from 'react';
import style from './Wod.module.css'

const Wod = (props) => {


    return (
        <Fragment>
        <div className={style.backdrop} onClick={props.onHide}></div>
        <div className={style.modal}>
            <a>Workout of the Day </a>
            <pre className={style.text}>{props.description}</pre>
        </div>
        </Fragment>
    )

}

export default Wod;