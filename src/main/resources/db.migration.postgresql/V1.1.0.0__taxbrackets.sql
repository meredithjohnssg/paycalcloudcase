create table taxbrackets (
  uuid VARCHAR(36) PRIMARY KEY,
  taxableincomestart  INT,
  taxableincomeend INT,
  taxbase INT,
  taxrate INT,
  taxyear INT
);
