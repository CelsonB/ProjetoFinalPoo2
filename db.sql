
drop database projetofinalpoo;

create database projetofinalpoo; 
use projetofinalpoo;


CREATE TABLE usuarios (
  id INT PRIMARY KEY auto_increment,
  nome_completo VARCHAR(100) NOT NULL,
  data_nascimento DATE NOT NULL,
  genero ENUM('M', 'F') NOT NULL,
  foto_pessoal BLOB,
  email VARCHAR(100) NOT NULL,
  nome_usuario VARCHAR(50) NOT NULL UNIQUE,
  senha VARCHAR(255) NOT NULL
);

CREATE TABLE agendas (
  id INT PRIMARY KEY auto_increment,
  nome VARCHAR(100) NOT NULL,
  descricao VARCHAR(255),
  id_usuario INT NOT NULL,
  FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

CREATE TABLE compromissos (
  id INT PRIMARY KEY auto_increment,
  titulo VARCHAR(100) NOT NULL,
  descricao TEXT,
  data_hora_inicio DATETIME NOT NULL,
  data_hora_termino DATETIME NOT NULL,
  local VARCHAR(100),
  id_agenda INT NOT NULL,
  data_hora_notificacao DATETIME,
  FOREIGN KEY (id_agenda) REFERENCES agendas(id)
);
CREATE TABLE convites (
  id INT PRIMARY KEY auto_increment,
  id_compromisso INT NOT NULL,
  id_usuario_convidado INT NOT NULL,
  status VARCHAR(10) NOT NULL CHECK(status IN ('aceito', 'recusado', 'pendente')),
  FOREIGN KEY (id_compromisso) REFERENCES compromissos(id),
  FOREIGN KEY (id_usuario_convidado) REFERENCES usuarios(id)
);


