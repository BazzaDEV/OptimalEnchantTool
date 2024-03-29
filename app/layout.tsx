import { cn } from "@/lib/utils"
import "./globals.css"
import type { Metadata } from "next"

import localFont from "next/font/local"
import { Toaster } from "@/components/ui/toaster"

const faithful = localFont({
  src: [
    {
      path: "../public/fonts/Faithful/Faithful32xRegular.woff",
      weight: "normal",
      style: "normal",
    },
    {
      path: "../public/fonts/Faithful/Faithful32xOblique.woff",
      weight: "normal",
      style: "oblique",
    },
    {
      path: "../public/fonts/Faithful/Faithful32xSemibold.woff",
      weight: "600",
      style: "normal",
    },
    {
      path: "../public/fonts/Faithful/Faithful32xSemiboldOblique.woff",
      weight: "600",
      style: "oblique",
    },
    {
      path: "../public/fonts/Faithful/Faithful32xBold.woff",
      weight: "bold",
      style: "normal",
    },
    {
      path: "../public/fonts/Faithful/Faithful32xBoldOblique.woff",
      weight: "bold",
      style: "oblique",
    },
  ],
  display: "swap",
})

export const metadata: Metadata = {
  title: "Optimal Enchant Tool",
  description:
    "A tool for Minecraft players who want to use an anvil for enchanting their items without the hassle.",
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html
      lang="en"
      className={cn(faithful.className, "flex flex-col p-4 max-w-5xl m-auto")}
    >
      <body>
        {children}
        <Toaster />
      </body>
    </html>
  )
}
