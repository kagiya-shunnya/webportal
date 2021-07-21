--/*タスクテーブル*/
--CREATE TABLE IF NOT EXISTS task (
--  id INT PRIMARY KEY,
--  user_id VARCHAR(50),
--  comment VARCHAR(200),
--  limitday DATE
--);

/* 開発用にデータ削除を追加 : リリース時は消す
DROP TABLE m_user;
DROP TABLE task;
 */

/* ユーザマスタ */
CREATE TABLE IF NOT EXISTS m_user (
    user_id VARCHAR(50) PRIMARY KEY,
    encrypted_password VARCHAR(100),
    user_name VARCHAR(50),
    darkmode BOOLEAN,
    role VARCHAR(50),
    enabled BOOLEAN
);

/* タスクテーブル */
CREATE TABLE IF NOT EXISTS task (
  id INT PRIMARY KEY,
  user_id VARCHAR(50),
  priority VARCHAR(10),
  title VARCHAR(50),
  comment VARCHAR(200),
  limitday DATE
);

/* 受験報告テーブル */
CREATE TABLE IF NOT EXISTS report (
  user_name VARCHAR(50),
  class_number VARCHAR(10),
  student_number VARCHAR(10),
  division VARCHAR(50),
  day DATE,
  company_name VARCHAR(50)
);

