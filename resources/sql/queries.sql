-- name:save-page!
-- creates a new page
INSERT INTO page
(url, content, timestamp)
VALUES (:url, :content, :timestamp)

-- name:get-pages
-- selects all available pages
SELECT * from page