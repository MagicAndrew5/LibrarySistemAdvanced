import { defineConfig } from "eslint/config";

export default defineConfig[
  {
    ...js.configs.recommended,
    files: ["**/*.js", "**/*.ts", "**/*.jsx", "**/*.tsx"],
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
];



	