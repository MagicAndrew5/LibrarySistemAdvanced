import { defineConfig } from "eslint/config";
import js from "@eslint/js"; // ✅ IMPORT MANCANTE AGGIUNTO

export default defineConfig([
  {
    ...js.configs.recommended,
    files: ["**/*.{js,ts,jsx,tsx}"], // più compatto
    ignores: ["dist/**"], // ⛔️ IGNORA LA CARTELLA DIST
    languageOptions: {
      ecmaVersion: "latest",
      sourceType: "module",
    },
  },
  {
    rules: {
      semi: "error",
      "prefer-const": "error",
    },
  },
]);
