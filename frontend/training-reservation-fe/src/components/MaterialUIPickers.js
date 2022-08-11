import * as React from "react";
import Stack from "@mui/material/Stack";
import TextField from "@mui/material/TextField";
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DesktopTimePicker } from "@mui/x-date-pickers/DesktopTimePicker";
import { DateTimePicker } from "@mui/x-date-pickers/DateTimePicker";
import { DesktopDatePicker } from "@mui/x-date-pickers/DesktopDatePicker";
import CalendarMonthIcon from "@mui/icons-material/CalendarMonth";
import ArrowLeftIcon from "@mui/icons-material/ArrowLeft";
import ArrowRightIcon from "@mui/icons-material/ArrowRight";
import { SxProps } from "@mui/system";

export default function MaterialUIPickers() {
  //const [value, setValue] = React.useState(new Date("04/01/2022 12:00:00"));
  const [value, setValue] = React.useState(new Date());

  const handleChange = (newValue) => {
    setValue(newValue);
  };

  return (
    <>
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        <Stack spacing={3}>
          <DesktopDatePicker
            label="Date"
            inputFormat="MM/DD/YYYY" //depends on date lib
            value={value}
            onChange={setValue}
            renderInput={(params) => {
              return <TextField {...params} />;
            }}
            views={["day", "month"]}
            showDaysOutsideCurrentMonth //very useful
            //@ts-ignore
            clearable //Typing seems to be missing for this
          />
          <DesktopTimePicker
            label="Time"
            value={value}
            onChange={handleChange}
            renderInput={(params) => <TextField {...params} />}
          />
          <DateTimePicker
            label="Date And Time Picker"
            value={value}
            onChange={handleChange}
            components={{
              OpenPickerIcon: CalendarMonthIcon,
              LeftArrowIcon: ArrowLeftIcon,
              RightArrowIcon: ArrowRightIcon
            }}
            renderInput={(params) => <TextField {...params} />}
          />
          <button onClick={()=>setValue(null)}>Clear</button>
        </Stack>
      </LocalizationProvider>
      <div style={{ marginTop: 50 }}>
        <a
          target="_blank"
          href="https://smartdevpreneur.com/the-ultimate-material-ui-v5-datepicker-and-timepicker-tutorial/"
        >
          Here's everything to know about styling the Date Picker!
        </a>
      </div>
    </>
  );
}