import { MenuItem } from "@mui/material";
import { TextField } from 'formik-mui';
import { useEffect, useState } from "react";
const Protocols = (props) => {

    const [protocols, setProtocols] = useState([]);

    useEffect(() => {
        const loadData = () => {
            setProtocols(["TCP", "UDP", "HTTP", "FTP", "SSH"])
        }
        loadData();
    }, []);

    return (
        <TextField
            select
            {...props}
        >
            {protocols.length ?
                protocols.map((protocol) => (
                    <MenuItem key={protocol} value={protocol}>
                        {protocol}
                    </MenuItem>
                ))
                :
                <MenuItem>loading...</MenuItem>
            }
        </TextField >
    );

};

export default Protocols;