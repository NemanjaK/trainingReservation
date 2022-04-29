import { Link, NavLink } from 'react-router-dom';
import styles from './Admin.module.css'
const Admin = () => {
  return (
    <main>
    <div>
        <div className={styles.bg}>
          <button className={styles.button}>
                <span>Add new member</span>
          </button>
          <button className={styles.button}>
                <span>Reservations</span>
          </button>
          <Link to='/members'><button className={styles.button}>
                <span>Members</span>               
          </button>
          </Link>
        </div>
    </div>
    </main>
  )
}

export default Admin;