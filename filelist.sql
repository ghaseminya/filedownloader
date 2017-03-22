CREATE TABLE `file` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `fpath` varchar(5000) NOT NULL,
  `downloaded` int(11) NOT NULL DEFAULT '0',
  `dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `file`
ADD PRIMARY KEY (`id`);
ALTER TABLE `file`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
