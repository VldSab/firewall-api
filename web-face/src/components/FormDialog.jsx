import * as React from "react";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import { Box, Button, TextField } from "@mui/material";
import { Formik, Field } from "formik";
import * as yup from "yup";
import useMediaQuery from "@mui/material/useMediaQuery";
import GroupType from "./util/GroupType";
import { sendGroup } from "../data/GroupsData"

const FormDialog = () => {
  const isNonMobile = useMediaQuery("(min-width:600px)");
  const [open, setOpen] = React.useState(false);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
    window.location.reload(false);
  };

  const handleFormSubmit = (values) => {
    sendGroup(values.groupName, [values.sourceIPs], [values.sourcePorts], null);
  };

  const handleCloseSubmit = (values) => {
    handleFormSubmit(values);
    handleClose();
  }

  return (
    <Box>
      <Button variant="outlined" onClick={handleClickOpen} color="secondary">
        ADD GROUP
      </Button>
      <Dialog
        open={open}
        onClose={handleClose}
        PaperProps={{ sx: { width: "20%", height: "60%" } }}
      >
        <DialogTitle>Create group</DialogTitle>
        <DialogContent>
          Create a New Group
          <Formik
            onSubmit={handleCloseSubmit}
            initialValues={initialValues}
            validationSchema={checkoutSchema}
          >
            {({
              values,
              errors,
              touched,
              handleBlur,
              handleChange,
              handleSubmit,
            }) => (
              <form onSubmit={handleSubmit}>
                <Box
                  display="grid"
                  gap="30px"
                  gridTemplateColumns="repeat(4, minmax(0, 1fr))"
                  sx={{
                    "& > div": {
                      gridColumn: isNonMobile ? undefined : "span 4",
                    },
                  }}
                >
                  <Field
                    value={values.method}
                    name="method"
                    type="text"
                    component={GroupType}
                    label="Method"
                    margin="normal"
                    variant="outlined"
                    disabled={false}
                    onChange={handleChange}
                    sx={{ gridColumn: "span 4" }}
                  />
                  <TextField
                    fullWidth
                    variant="filled"
                    type="text"
                    label="Group name"
                    onBlur={handleBlur}
                    onChange={handleChange}
                    value={values.groupName}
                    name="groupName"
                    error={!!touched.groupName && !!errors.groupName}
                    helperText={touched.groupName && errors.groupName}
                    sx={{ gridColumn: "span 4" }}
                  />
                  <TextField
                    fullWidth
                    variant="filled"
                    type="text"
                    label="IPs"
                    onBlur={handleBlur}
                    onChange={handleChange}
                    value={values.sourceIPs}
                    name="sourceIPs"
                    style={{
                      display: values.method === "Ports" ? "none" : "block",
                    }}
                    error={!!touched.sourceIPs && !!errors.sourceIPs}
                    helperText={touched.sourceIPs && errors.sourceIPs}
                    sx={{ gridColumn: "span 4" }}
                  />
                  <TextField
                    fullWidth
                    variant="filled"
                    type="text"
                    label="Ports"
                    onBlur={handleBlur}
                    onChange={handleChange}
                    value={values.sourcePorts}
                    name="sourcePorts"
                    style={{
                      display: values.method === "IPs" ? "none" : "block",
                    }}
                    error={!!touched.sourcePorts && !!errors.sourcePorts}
                    helperText={touched.sourcePorts && errors.sourcePorts}
                    sx={{ gridColumn: "span 4" }}
                  />
                </Box>
                <Box  display="flex" justifyContent="center" mt="40px" >
                  <Button
                    type="submit"
                    color="secondary"
                    variant="contained"
                    sx={{ width: "25%", height: "100%" }}
                  >
                    Create
                  </Button>
                </Box>
              </form>
            )}
          </Formik>
        </DialogContent>
        {/* <DialogActions>
          <Button onClick={handleClose} color="secondary">
            Cancel
          </Button>
          <Button onClick={handleCloseSubmit} color="secondary">
            Ok
          </Button>
        </DialogActions> */}
      </Dialog>
    </Box>
  );
};

const checkoutSchema = yup.object().shape({
  groupName: yup.string(),
  method: yup.string(),
  sourcePorts: yup.string(),
  sourceIPs: yup.string(),
});
const initialValues = {
  groupName: "",
  method: "",
  sourcePorts: "",
  sourceIPs: "",
};

export default FormDialog;
